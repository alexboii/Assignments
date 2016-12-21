import java.util.*;




public class Partone {

	public Queue<Character> q;
	public Stack<Character> s;
	char topItem;
	char token;
	
	public Partone(){
		this.s = new Stack<Character>();
		this.q = new LinkedList<Character>();
	}
	
	public void scan(String input){
		for(char token: input.toCharArray()){
			switch(token){
			case '(': q.add(token); break;
			case '{': q.add(token); break;
			case '[': q.add(token); break;
			case ')': q.add(token); break;
			case ']': q.add(token); break;
			case '}': q.add(token); break;
			}
		}
			
	}
	
	public boolean check(String input){
		
		this.q.clear();
		this.s.clear();
		
		scan(input);
		
		while(!this.q.isEmpty()){

			this.token = q.remove();
			
			if(token == '(' || token == '[' || token == '{'){
				this.s.push(token);
			}
			else
			{
				if(this.s.isEmpty())
				{
					if(token == ')')
					{
						System.out.println("Missing opening bracket of type (\n");
					}
					if(token == ']')
					{
						System.out.println("Missing opening bracket of type [\n");
					}
					if(token == '}')
					{
						System.out.println("Missing opening bracket of type {\n");
					}
					
					return false;
				}
				else
				{
					this.topItem = s.pop();
					
					switch(this.topItem){
					case '(': 
						if(token == ')'){
							break;
						}
						else{
							System.out.println("Missing opening bracket of type )\n");
							return false;
						}
						
					case '{': 
						if(token == '}'){
							break;
						}
						else{
							System.out.println("Missing opening bracket of type }\n");
							return false;
						}

					case '[': 
						if(token == ']'){
							break;
						}
						else{
							System.out.println("Missing opening bracket of type ]\n");
							return false;
						}
					default: 
						System.out.println("Mismatched brackets:" + s.peek() + " "+ q.peek());
						return false;
					
				}

					
					
			}
					
				}
		
			
			}
		
		if(this.s.isEmpty())
		{
			System.out.println("Balanced brackets\n");
			return true;
		} 
		
		else{
			
			if(s.peek() == '{')
			{
				System.out.println("Missing closing bracket of type }\n");
			}
			if(s.peek() == '[')
			{
				System.out.println("Missing closing bracket of type ]\n");
			}
			if(s.peek() == '(')
			{
				System.out.println("Missing closing bracket of type )\n");
			}
			
		return false;
		
	
		}
		
	}

}
