package impls.toshiba;


import interfaces.Hand;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component

public class ToshibaHand implements Hand {
	
	public void catchSomething(){
		System.out.println("Catched from Toshiba!");
	}

}
