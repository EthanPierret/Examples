import java.io.*;
import java.util.*;

public class BasicComputerNew
	{	
	
		/**
		To begin we define each variable that we will need throughout the code
		And also make an instance of the Scanner
		*/
		
		static Scanner input = new Scanner(System.in);
		static int accumulator = 0;
		static int operator = 0;
		static int operand = 0;
		static int programcounter = 0;
		static int ir;
		static boolean error = false;
		static int[] memory = new int[100];
		
		/**
		We start the main method by explaining that it will throw an IOException to enable correct compiling
		The main method will initially attempt to read from a default file. If this does not work then the program will 
		prompt the user for a file name.
		
		
		The main method will read from a file line by line into memory. Then the mainloop is applied for each piece of 
		the memory.
		*/

		public static void main(String args[])throws IOException 
			{
			
				String userinput;
				
				File f = new File("C:/BasicComputerprograms/program.txt");

				/**This if statement checks to see if the default file f, already exists sif so its will run the program */

				if(f.exists() && !f.isDirectory())
				{
					Scanner defaultfile = new Scanner(f);

					// This while loop tells the scanner to read the file line for line while saving each line to a different spot in memory.
					while(defaultfile.hasNext())
					{
						for(int i=0; i<99; i++ )
						{
							if(defaultfile.hasNext())
							{
								memory[i] = defaultfile.nextInt();
								
							}
						
							else
							{ 
								memory[i] = 0;
								
							}
						}
					}
				}

				/**This else statement is set up to prompt the user for a file name, should the default file not be present */
				
				else
				{
					System.out.println("What is the the name of the file you would like to load? Please include the file extension.");
						
					userinput = input.nextLine().trim();
						
					File userfile = new File(userinput);
						
					Scanner usercodes = new Scanner(userfile);

					// This while loop tells the scanner to read the file line for line while saving each line to a different spot in memory.
					while(usercodes.hasNext())
					{
						for( int i = 0 ; i < 99; i++ )
						{
							if(usercodes.hasNext())
							{
								memory[i] = usercodes.nextInt();
							}
							else
								memory[i] = 0;

						}
					}
				}

				mainLoop();
				
				
			}
			
			/**
			This is the main loop that impliments each part of the basic computer
			Start by testing for invalid program counter
			Then we start out with each ir and send it to the decoding stage
			After it is seperated into the operator and operand, they are sent to exe
			In the exe the specific instruction is processed and executed
			If the code ends then it won't go further
			If it doesn't end, then we test to see if error = true and if it is false then the main loop runs again until finished
			*/
			
		public static void mainLoop()
			{
				if(programcounter<0 || programcounter>99)
				{ 
					end(7,0);
				}
			
				else
				{
					ir = memory[programcounter];
				
					decode();
				
					exe();
	
					while(error != true)
					{
						mainLoop();
					}
				}
			}
		
		/**
		The end method was devleoped to identify errors in the machine code which is being loaded into the program.
		Whenever a specific end code is triggered it will end the program, will display a message specific to the error type
		and the memory dump.
		@param code is the code to define the type of end
		@param location is the certain place in memory that the end takes place
		*/
		
		private static void end(int code, int location) 
		{
			/** Creates end command for ending the program normally. */
			if (code == 0)
			{
				System.out.println("Basic Computer Complete!");
			}

			/** Creates end command should the program attempt to divide by zero */
			else if ( code == 1)
			{
				System.out.println("Basic Computer abnormally terminated with fatal error");
				System.out.println("Attempt to divide by zero");
			}	
			
			/** Creates end command should the program attempt to execute an invalid operation code */
			else if ( code == 2)
			{
				System.out.println("Basic Computer abnormally terminated with fatal error");
				if ( location > -1) 
				{
					System.out.println("Attempt to execute invalid operation codes at location " + location);
				}
				
				else
				{ 
				System.out.println("Attempt to execute invalid operation codes");
				}

			}

			/** Creates end command should the program encounter an Accumulator over flow (Greater than 9999) */
			else if ( code == 3 )
			{
				System.out.println("Basic Computer abnormally terminated with fatal error");
				System.out.println("Accumulator over flow");
			}

			/** Creates end command should the program encounter Accumulator under flow (Below -9999) */
			else if (code == 4)
			{
				System.out.println("Basic Computer abnormally terminated with fatal error");
				System.out.println("Acummulator under flow");
			}	
			
			/** Creates end command should the program encounter a memory over flow at a specific destination */
			else if (code == 5)
			{
				System.out.println("Basic Computer abnormally terminated with fatal error");
				System.out.println("Memory over flow at: " + location);
			}
			
			/** Creates end command should the program encountere a memory under flow at a specific destination */
			else if ( code == 6)
			{
				System.out.println("Basic Computer abnormally terminated with fatal error");
				System.out.println("Memory under flow at: "+ location);
			}

			/** Creates end command should the program encounter an invalid accumulator value */
			else if ( code == 7)
			{
				System.out.println("Basic Computer abnormally terminated with fatal error");
				System.out.println("Invalid Accumulator");
			}
			
			/** Creates end command should the program encounter a bad memory destination */
			else if ( code ==8 ) 
			{
				System.out.println("Basic Computer abnormally terminated with fatal error");
				System.out.println("Bad Memory");
			}
			
			/** Final end command which is given if no other end code is met */
			else
			{ 
				System.out.println("Bad error code" + code); 
			}

			// This is the memory dump which is displayed for the user should the program end prematurely
			System.out.println("Registers:\n\n" + "Accumulator                        " + accumulator + "\n"
				+ "Program Counter                    " + programcounter + "\n" + "Instruction Register               "
				+ ir + "\n" + "Operation Code                     " + operator + "\n"
				+ "Operand                            " + operand + "\n\n\n");
			System.out.println("Memory Dump\n" + "       0     1     2     3     4     5     6     7     8     9\n");

			/** for loop is for displaying the side numbers and the chart of all of the
			memory array */
			for (int i = 0; i <= 99; i++) 
			{
				if ((i % 10) == 0 && i != 0)
					System.out.print("\n" + i + "     " + memory[i] + "     ");
				
				else if ((i % 10) != 0 && i != 0)
					System.out.print(memory[i] + "     ");
				
				else if ((i % 10) == 0 && i == 0)
					System.out.print("\n" + i + "0     " + memory[i] + "     ");
			}
		
			System.exit(0);	
		
			error = true;
	
	}

		/**
		This part of the program will test to see if the Information Register is invalid and will return an error if it is
		Then if it is valid it will take and change it into the operator and operand by dividing by 100 and % by 100
		At the end it will increment the program counter in to prepare for the next run through
		It has a return type of void
		*/
		
		public static void decode()
		{
			if(ir < 99 || ir > 5100)	//Because inputs only go up to 5000 and the smallest is bigger than 99
			{
			
				end(2,programcounter);
				
			}
			else
			{
			operator=(ir/100);			//Operator is the first half of the code
				
		    operand=(ir%100);			//Operand is the second half and will be memory location
				
			}
			
			programcounter = programcounter + 1;
		}
			
		
		/**
		This part will execute codes from mainloop() per instructions. It will only take control away from mainloop() is there is a error. If there is,
		it will call end(). It does not return anything and only operates/loops once before giving control away to another fucntion.
		*/
		public static void exe() 
		{
			if (operator == 34) 
			{
				//loops for as many times listed in accumulator
				//for i=accumulator; I >= 0; i - -
				for (int i = accumulator; i>0;i--) 
				{
					//print number
				//System.out.Print(memory[operand+(accumulator-i)])
				System.out.print(memory[operand+(accumulator-i)]);
				}
		
				System.out.println();
			}
			
			else if (operator == 35) 
			{ 
				//loops for as many times listed in accumulator
				//for i=accumulator; I >= 0; i - -
				for (int i = accumulator; i>0;i--) 
				{
					//print CHAR of number
					//System.out.Print(memory[operand+(accumulator-i)])
					System.out.print((char) memory[operand+(accumulator-i)]);
				}
				
				System.out.println();
			}

			else if (operator == 33)
			{
				// number intake to memory
				System.out.println("Please Enter a Number!");
				
				//memory[operand] = scanner.nextint()
				
				Scanner keyboard = new Scanner(System.in);
				
				memory[operand] = keyboard.nextInt();

			} 
			
			else if (operator == 32)
			{
				//system.out.print(memory[operand])
				//number output from memory
		
				System.out.println("From " + operand + " in memory :" + memory[operand]);
			}
			
			else if (operator == 31) 
			{
				// accumulator = memory[operand];
				// load memory into accumulator
		
				accumulator = memory[operand];
			}
			
			else if (operator == 30)
			{
				//memory[operand] = accumulator
				//load accumulator into memory
				
				memory[operand] = accumulator;
			} 
			
			else if (operator == 21)
			{
				//
				//accumulator = accumulator + memory[operand]
				// + opperator with error checking
				accumulator = accumulator + memory[operand];
		
				if(accumulator>9999)
				{
					end(3,accumulator);
				}
				
				else if(accumulator<-9999)
				{
					end(4,accumulator);
				}
		
			}
		
			else if (operator == 20)
			{
				//accumulator = accumulator - memory[operand]
				accumulator = accumulator - memory[operand];
				// - opperator with error checking
				
				if(accumulator>9999)
				{
					end(3,accumulator);
				}
				
				else if(accumulator<-9999)
				{
					end(4,accumulator);
				}
				
			}
			
			else if (operator == 11)
			{
				//accumulator = accumulator / memory[operand]
				// / opperator with error checking
				accumulator = accumulator / memory[operand];
		
				if(accumulator>9999)
				{
					end(3,accumulator);
				}
				
				else if(accumulator<-9999)
				{
					end(4,accumulator);
				}

			}
		
			else if (operator == 29)
			{
				//accumulator = operand
				//load agrument into accumulator
				accumulator = operand;
			
			}
			
			else if (operator == 06)
			{
				// accumulator = accumulator + operand
				// + opperator with argument and error checking
				accumulator = accumulator + operand;
		
				if(accumulator>9999)
				{
					end(3,accumulator);
				}
				
				else if(accumulator<-9999)
				{
					end(4,accumulator);
				}

			}
		
			else if (operator == 07)
			{
				// accumulator = accumulator - operand
				// - opperator with argument and error checking
				accumulator = accumulator - operand;
		
				if(accumulator>9999)
				{
					end(3,accumulator);
				}
				
				else if(accumulator<-9999)
				{
					end(4,accumulator);
				}

			} 
		
			else if (operator == 8)
			{
				//accumulator = accumulator * operand
				accumulator = accumulator * operand;
				// * opperator with argument and error checking
				if(accumulator>9999)
				{
					end(3,accumulator);
				}
				
				else if(accumulator<-9999)
				{
					end(4,accumulator);
				}
	
			} 

			else if (operator == 9)
			{
				// accumulator = accumulator / operand
				// / opperator with argument and error checking
				accumulator = accumulator / operand;
		
				if(accumulator>9999)
				{
					end(3,accumulator);
				}
				
				else if(accumulator<-9999)
				{
					end(4,accumulator);
				}

			}	
		
			else if (operator == 10)
			{
				// accumulator = accumulator * memory[operand]
				// * opperator with error checking
				accumulator = accumulator * memory[operand];
		
				if(accumulator>9999)
				{
					end(3,accumulator);
				}
		
				else if(accumulator<-9999)
				{
					end(4,accumulator);
				}

			}
			
			else if (operator == 43)
			{
				//Programcounter = operand
				programcounter = operand;

			}
			
			else if (operator == 42)
			{
				//if accumulator < 0
				//branch if negative
				
				if(accumulator < 0)
				{
					//Programcounter = operand
					programcounter = operand;
				}

			}
			
			else if (operator == 41)
			{
				//if accumulator > 0
				//branch if possative
				if(accumulator > 0) 
				{
					//Programcounter = operand
					programcounter = operand;
				}

			}
			
			else if (operator == 40)
			{
				//if accumulator == 0
				//branch if 0
				if(accumulator == 0)
				{
					//Programcounter = operand
					programcounter = operand;
				}

			} 

			else if (operator == 25)
			{
				//memory[operand] = memory[operand] + 1
				//Incriment memory by 1
				memory[operand] = memory[operand] + 1;
		
				if(memory[operand]>99)
				{
					end(5,memory[operand]);
				}
				
				else if(memory[operand]<-99)
				{	
					end(6,memory[operand]);
				}

			} 

			else if (operator == 26)
			{
				//memory[operand] = memory[operand] - 1
				//Decriment memory by 1
				memory[operand] = memory[operand] - 1;
		
				if(memory[operand]>99)
				{
					end(5,memory[operand]);
				}
				
				else if(memory[operand]<-99)
				{
					end(6,memory[operand]);
				}

			
			} 

			else if (operator == 50)
			{
				//End(0) // no error
				end(0,0); // no error
				
			} 

			else 
			{
				//end(2)//bad code(s)
				end(2,0);//bad code(s)
				
			}

		}

	}