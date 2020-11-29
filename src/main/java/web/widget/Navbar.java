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
            new Item("Shop", "/Shop", false, false),
            new Item("Log ind", "/Login", false, false),
            new Item("Employee", "/lists", true, false),
            new Item("Admin", "/admin", true, true)
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
