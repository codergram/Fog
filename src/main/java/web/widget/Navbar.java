package web.widget;

import domain.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Navbar {
    private final HttpServletRequest request;
    
    public Navbar(HttpServletRequest request) {
        this.request = request;
    }
    
    private final List<Item> items = List.of(
            new Item("Home", "/", false, false),
            new Item("Byg din carport", "/Shop", false, false),
            new Item("Log ind", "/Login", false, false),
            new Item("Log ud", "/", true, false),
            new Item("Employee", "/lists", true, false),
            new Item("Admin", "/admin", true, true),
            new Item("Orders", "/Orders", true, false),
            new Item("Customers", "/Customers", true, false),
            new Item("Users", "/Users", true, false),
            new Item("Stats", "/Statestic", true, true)
    );
    
    public List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        User user = (User) request.getSession().getAttribute("user");
        
        if(user != null){
            if (user.isAdmin()) {
                    for(Item x: items){
                        if(x.authorizedOnly){
                            list.add(x);
                        }
                    }
            } else if (user.isEmployee()) {
                for (Item x : items) {
                    if (x.authorizedOnly && ! x.adminOnly) {
                        list.add(x);
                    }
                }
            } else {
                for (Item x : items) {
                    if (!x.adminOnly && !x.authorizedOnly) {
                        list.add(x);
                    }
                }
            }
        } else {
            for (Item x : items) {
                if (!x.adminOnly && !x.authorizedOnly) {
                    list.add(x);
                }
            }
        }
        return list;
    }
    
    public class Item {
        private final String name;
        private final String url;
        private final boolean authorizedOnly;
        private final boolean adminOnly;
        
        public Item(String name, String url, boolean authorizedOnly, boolean adminOnly) {
            this.name = name;
            this.url = url;
            this.authorizedOnly = authorizedOnly;
            this.adminOnly = adminOnly;
        }
        
        public String getUrl() {
            return url;
        }
        
        public String getName() {
            return name;
        }
        
        public boolean isActive() {
            return request.getRequestURI().endsWith(url);
        }
    }
}
