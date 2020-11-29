package web.widget;

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
            new Item("Bygning Carport", "/Shop", false, false),
            new Item("Log ind", "/Login", false, false),
            new Item("Log ud", "/", true, true),
            new Item("Employee", "/lists", true, true),
            new Item("Admin", "/admin", true, true),
            new Item("Orders", "/Orders", true, true),
            new Item("Customers", "/Customers", true, true),
            new Item("Users", "/Users", true, true),
            new Item("Statestic", "/Statestic", true, true)


    );
    
    public List<Item> getItems() {
        String name = (String) request.getSession().getAttribute("user");
        if (name != null && name.equals("ADMIN")) {
            return items;
        } else {
            List<Item> list = new ArrayList<>();
            for (Item x : items) {
                if (!x.adminOnly) {
                    list.add(x);
                }
            }
            return list;
        }
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
