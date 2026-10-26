import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	ArrayList<Double> x = new ArrayList<Double>();
	ArrayList<Double> y = new ArrayList<Double>();
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {
			int nico = Integer.parseInt(in.readLine());
			x.add(0.0);
			x.add(100.0);
			y.add(0.0);
			y.add(0.0);
			
			draw(nico);
			
			for (int i = 0; i < x.size(); i++) {
				System.out.println(x.get(i) + " " + y.get(i));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void draw(int nico) {
		if (nico == 0) return;
		
		for (int i = 1; i < x.size(); i++) {
			double x0 = x.get(i - 1);
			double y0 = y.get(i - 1);
			
			double x1 = x.get(i);
			double y1 = y.get(i);
			
			double dx = (x1 - x0) / 3;
			double dy = (y1 - y0) / 3;
			
			double rad = (Math.PI / 3);

			x.add(i, x0 + dx);
			y.add(i, y0 + dy);
			i++;

			double rx = Math.cos(rad) * dx - Math.sin(rad) * dy;
			double ry = Math.sin(rad) * dx + Math.cos(rad) * dy;
			
			x.add(i, x0 + dx + rx);
			y.add(i, y0 + dy + ry);
			i++;
			
			x.add(i, x1 - dx);
			y.add(i, y1 - dy);
			i++;
		}
		
		draw(nico - 1);
	}
}