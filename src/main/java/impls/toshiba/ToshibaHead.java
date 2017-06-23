package impls.toshiba;


import interfaces.Head;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component

public class ToshibaHead implements Head {
	
	public void calc(){
		System.out.println("Thinking about Toshiba...");
	}

}
