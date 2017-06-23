package impls.toshiba;


import interfaces.Leg;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component

public class ToshibaLeg implements Leg {
	
	public void go(){
		System.out.println("Go to Toshiba!");
	}

}
