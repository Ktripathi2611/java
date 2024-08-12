import java.awt.*;

class FileApp {
    FileApp() {
        Frame f = new Frame("File Scan and View Help");
        MenuBar mb = new MenuBar();

        Menu fi = new Menu("File");
        Menu s = new Menu("Scan");
        Menu v = new Menu("View");
        Menu h = new Menu("Help");

        MenuItem o = new MenuItem("Open");
        MenuItem n = new MenuItem("New");
        MenuItem sa = new MenuItem("Save");

        fi.add(o);
        fi.add(n);
        fi.add(sa);
        mb.add(fi);
        mb.add(s);
        mb.add(v);
        mb.add(h);

        f.setMenuBar(mb);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        new FileApp();
    }
}
