
package com.eyup.crudweb;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final CustomerRepository repository;
    private final Grid<Customer> grid = new Grid<>(Customer.class, false);

    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");

    private Customer selectedCustomer;

    public MainView(CustomerRepository repository) {
        this.repository = repository;

        H2 title = new H2("Customer Management");

        grid.addColumn(Customer::getId).setHeader("Id");
        grid.addColumn(Customer::getFirstName).setHeader("First Name");
        grid.addColumn(Customer::getLastName).setHeader("Last Name");

        grid.setSizeFull();

        grid.asSingleSelect().addValueChangeListener(event -> {
            selectedCustomer = event.getValue();

            if (selectedCustomer != null) {
                firstName.setValue(selectedCustomer.getFirstName());
                lastName.setValue(selectedCustomer.getLastName());
            }
        });

        Button newCustomer = new Button("New customer", event -> {
            selectedCustomer = null;
            grid.deselectAll();
            firstName.clear();
            lastName.clear();
        });

        Button save = new Button("Save", event -> {
            if (firstName.isEmpty() || lastName.isEmpty()) {
                return;
            }

            Customer customer;

            if (selectedCustomer == null) {
                customer = new Customer();
            } else {
                customer = selectedCustomer;
            }

            customer.setFirstName(firstName.getValue());
            customer.setLastName(lastName.getValue());

            repository.save(customer);

            refreshGrid();
            grid.deselectAll();
            selectedCustomer = null;
            firstName.clear();
            lastName.clear();
        });

        Button delete = new Button("Delete", event -> {
            if (selectedCustomer != null) {
                repository.delete(selectedCustomer);

                refreshGrid();
                grid.deselectAll();
                selectedCustomer = null;
                firstName.clear();
                lastName.clear();
            }
        });

        HorizontalLayout fields = new HorizontalLayout(firstName, lastName);
        HorizontalLayout buttons = new HorizontalLayout(newCustomer, save, delete);

        add(title, grid, fields, buttons);

        setSizeFull();
        refreshGrid();
    }

    private void refreshGrid() {
        grid.setItems(repository.findAll());
    }
}
