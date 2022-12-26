package com.example.application.views.list;

import ActorModel.ActorProxy;
import ActorModel.Factory.AbstractContext;
import ActorModel.Factory.AbstractContextFactory;
import ActorModel.Factory.VirtualContextFactory;
import com.example.application.data.entity.ActorEntry;
import com.example.application.data.service.ActorService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.Map;

@PageTitle("Actors | ActorFrontend")
@Route(value = "", layout= MainLayout.class)
@RouteAlias(value="",layout =MainLayout.class)
@PermitAll
public class ListView extends VerticalLayout {
    Grid<ActorEntry> grid = new Grid<ActorEntry>();
    TextField filterText = new TextField();
    ActorForm form;
    private ActorService service;
    private static AbstractContextFactory factory = new VirtualContextFactory();
    public static AbstractContext context=factory.create();
    public static Map<ActorEntry,ActorProxy>proxies=new HashMap<>();

    public ListView(ActorService service) {
        context=factory.create();
        this.service = service;
        addClassName("list-view"); //Para después poder darle estilo en el archivo css
        setSizeFull();

        configureGrid();
        configureForm();
        add(
                getToolbar(),
                getContent()
        );
//launchThread();
        updateList();
        closeEditor();

//        ServletContext servletContext= VaadinServlet.getCurrent().getServletContext();
//        ExecutorService executorService= Executors.newCachedThreadPool();
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                while(true){
//                    try {
//                        Thread.sleep(3000);
//                        updateList();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        });

    }


    private void closeEditor() {
        form.setActor(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    /**
     * Configura el formulario
     */
    private void updateList() {
        grid.setItems(service.findAllActors(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2,grid); //Para que el grid ocupe el 2/3 del espacio
        content.setFlexGrow(1,form); //Para que el form ocupe el 1/3 del espacio
        content.addClassName("content");    //Para después poder darle estilo en el archivo css
        content.setSizeFull();  //Para que el contenido ocupe tod0 el espacio disponible

        return content;

    }

    private void configureForm() {
        form=new ActorForm();
        form.setWidth("25em");
        form.addListener(ActorForm.SaveEvent.class,this::saveActor);
        form.addListener(ActorForm.DeleteEvent.class,this::deleteActor);
        form.addListener(ActorForm.CloseEvent.class,this::closeEvent);
        form.addListener(ActorForm.UpdateEvent.class,this::updateEvent);
    }
    public void updateEvent(ActorForm.UpdateEvent event){
        updateList();
    }
    private void closeEvent(ActorForm.CloseEvent evt){
        closeEditor();
        grid.asSingleSelect().clear();

    }

    private void deleteActor(ActorForm.DeleteEvent event) {
        service.deleteActor(event.getActor());
        updateList();
        closeEditor();
    }


    private void saveActor(ActorForm.SaveEvent event) {
        service.saveActor(event.getActor());
        updateList();
        closeEditor();
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());

        Button add_contactButton = new Button("Add actor");
        add_contactButton.addClickListener(e->addActor());    //Cuando se haga click en el botón se ejecutará el método addContact

        HorizontalLayout toolBar = new HorizontalLayout(filterText, add_contactButton);
        toolBar.addClassName("toolbar");

        return toolBar;
    }


    private void addActor() {
        grid.asSingleSelect().clear();
        editActor(new ActorEntry());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();


        grid.addColumn(contact -> contact.getProxyId()).setHeader("Proxy name");
        grid.addColumn(contact -> contact.getActorType()).setHeader("Actor type");
        //grid.addColumn(contact -> contact.getStatus().toString()).setHeader("Status");
        grid.addComponentColumn(contact->contact.getStatusButton()).setHeader("Status");
//        grid.addColumn(contact->contact.getProgressBar()).setHeader("Progress");
        grid.addComponentColumn(contact->contact.getProgressBar()).setHeader("Sent messages");

        /*grid.addColumn(contact -> contact.getProxy().getProxyId()).setHeader("Status");
        grid.addColumn(contact -> contact.getStatus().toString()).setHeader("Company");*/
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event->editActor(event.getValue()));


    }


    private void editActor(ActorEntry contact) {
        if(contact==null){
            closeEditor();
        }

        else{
            if(contact.getActor()!=null)
                form.editAlreadyExistingActor(contact);

            else
                form.setActor(contact);
            form.setVisible(true);
            addClassName("editing");
        }


    }
}
