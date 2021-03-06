import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a + bx + cx**2 + dx**3 + ...
 * @author Laurent Verweijen
 *
 */
public class Polynomial {
	List<Long> polys;
	
	public Polynomial() {
		this(new ArrayList<Long>());
	}

	public Polynomial(List<Long> args) {
		polys = args;
	}
	
	@Override
	public String toString() {
		return "P" + polys;
	}
	
	public Long get(int index) {
		if (index < polys.size())
			return polys.get(index);
		else
			return 0l;
	}
	
	public void set(int index, Long value) {
		while(index >= polys.size())
			polys.add(0l);
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
	
	private Polynomial multiply(Long n, int exp) {
		Polynomial res = new Polynomial();
		for(int i = 0; i <= order(); i++)
			res.set(i + exp, n * get(i));
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
	
	public int compare(Polynomial other) {
		if(this.order() > other.order()) 
			return 1;
		else if(this.order() < other.order())
			return -1;
		else {
			for(int k = order(); k >= 0; k--) {
				if(this.get(k) > other.get(k)) 
					return 1;
				else if(this.get(k) < other.get(k))
					return -1;
			}
		}
		return 0;
	}
	
	public Polynomial mod(Polynomial other, int m) {
		Polynomial current = this;

		while(current.compare(other) > 0)
		{
			Polynomial sub = other.multiply(
					current.get(current.order()) / other.get(other.order()), 
					current.order() - other.order());
			current = current.minus(sub);
		}
		
		for(int i = 0; i <= current.order(); i++)
			current.set(i, ((current.get(i) % m) + m) % m);
		
		return current;
	}
	
	public Polynomial modular_pow(int r, Polynomial mod, int m) {
		Polynomial base = this;
		Polynomial res = new Polynomial(Arrays.asList(new Long[]{1l}));
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
		Polynomial p = new Polynomial(Arrays.asList(new Long[] {3l, 12l, 4l, 1l}));
		Polynomial h = p.multiply(p);
		System.out.println(h);
		Polynomial g = new Polynomial(Arrays.asList(new Long[] {-1l, 0l, 0l, 0l, 0l, 1l}));
		Polynomial f = h.mod(g, 17);
		System.out.println(f);
	}
	
}
