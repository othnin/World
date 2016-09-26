
import java.awt.*;
import controllers.*;
import models.Model;
import view.*;


public class world {
	
	public static void main(String[] args) {
		new world().buildGUIandRun();
	}
	
	public void buildGUIandRun() {	
		EventQueue.invokeLater(new Runnable() {
			@Override
	        public void run() {
				Model model = new Model();
				View view = new View();
				Controller controller = new Controller(view, model);
			}
		});
	}

}
