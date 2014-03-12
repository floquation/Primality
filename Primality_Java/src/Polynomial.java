import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a + bx + cx**2 + dx**3 + ...
 * @author laurent
 *
 */
public class Polynomial {
	List<Integer> polys;
	
	public Polynomial() {
		this(new ArrayList<Integer>());
	}

	public Polynomial(List<Integer> args) {
		polys = args;
	}
	
	@Override
	public String toString() {
		return "P" + polys;
	}
	
	public Integer get(int index) {
		if (index < polys.size())
			return polys.get(index);
		else
			return 0;
	}
	
	public void set(int index, Integer value) {
		while(index >= polys.size())
			polys.add(0);
		polys.set(index, value);
	}
	
	public int order() {
		int order = polys.size() - 1;
		while (order >= 0 && get(order) == 0)
			order--;
		return order;
	}
	
	public Polynomial multiply(Polynomial other) {
		Polynomial res = new Polynomial();
		for(int i = 0; i <= order(); i++)
			for(int j = 0; j <= other.order(); j++)
				res.set(i + j, res.get(i + j) + get(i) * other.get(j));
		return res;
	}
	
	public Polynomial multiply(Integer n) {
		Polynomial res = new Polynomial();
		for(int i = 0; i <= order(); i++)
			res.set(i, n * get(i));
		return res;
	}
	
	public Polynomial shift(int n) {
		Polynomial res = new Polynomial();
		for(int i = 0; i <= order(); i++)
			res.set(i + n, get(i));
		return res;
	}
	
	public Polynomial minus(Polynomial other) {
		int max = Math.max(order(), other.order());
		Polynomial res = new Polynomial();
		for(int i = 0; i <= max; i++)
			res.set(i, get(i) - other.get(i));
		return res;
	}
	
	public Polynomial mod(Polynomial other, Integer m) {
		Polynomial current = this;
		
		while(current.order() > other.order() || 
				(current.order() == other.order() && 
				 current.get(current.order()) > other.get(other.order())))
		{
		
			Polynomial sub = other
					.shift(current.order() - other.order())
					.multiply(current.get(current.order()) / other.get(other.order()));
			current = current.minus(sub);
		}
		
		for(int i = 0; i <= current.order(); i++)
			current.set(i, ((current.get(i) % m) + m) % m);
		
		return current;
	}
	
	public Polynomial modexp(int r, Polynomial mod, int m) {
		Polynomial base = this;
		Polynomial res = new Polynomial(Arrays.asList(new Integer[]{1}));
		while(r > 0) {
			if(r % 2 == 1)
				res = res.multiply(base).mod(mod, m);
			base = base.multiply(base).mod(mod, m);
			r /= 2;
		}
		return res;
	}

	// Some informal testing
	public static void main(String[] args) {
		Polynomial p = new Polynomial(Arrays.asList(new Integer[] {3, 12, 4, 1}));
		Polynomial h = p.multiply(p);
		System.out.println(h);
		Polynomial g = new Polynomial(Arrays.asList(new Integer[] {-1, 0, 0, 0, 0, 1}));
		Polynomial f = h.mod(g, 17);
		System.out.println(f);
	}
	
}
