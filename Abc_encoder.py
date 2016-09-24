'''
Created on Sep 18, 2016
'''
import random

'''
I could have used a list or text file like this:

possibleA = ["f", "k", "n"]
possibleB = ["q", "r", "z"]
possibleC = ["s", "y", "v"]


Or:


targetfile = open(file, "w")

info = targetfile.readline()
do_stuff(info)
targetfile.write(stuff)

targetfile.close()

Since this is a simple program i went with simple code.
'''


def randomtxt(text) :
    
    charaters = (len(text))
    printthis = ""

    for i in range(0,charaters) :

        Arand = random.randint(1,3)
        newchar = ""
        
        if text[i] == "a" :
            
            if Arand == 1 :
                newchar = "f"
            if Arand == 2 :
                newchar = "k"
            if Arand == 3 :
                newchar = "n"



        if text[i] == "b" :
            
            if Arand == 1 :
                newchar = "q"
            if Arand == 2 :
                newchar = "r"
            if Arand == 3 :
                newchar = "z"



        if text[i] == "c" :
            
            if Arand == 1 :
                newchar = "s"
            if Arand == 2 :
                newchar = "y"
            if Arand == 3 :
                newchar = "v"
            


        if newchar == "" :
            print("Error/ Illegal Character - " + text[i])
            
            if text[i].istitle() == True :
                print("Upper case letters are not supported.")



        printthis = printthis + newchar
        newchar = ""

    print(printthis)
    
    
    
def unrandomtxt(text):
    
    charaters = (len(text))
    printthis = ""

    for i in range(0,charaters) :

        newchar = ""
        
        if text[i] == "f" or "k" or "n": 
            newchar = "a"
                 
        if text[i] == "q" or "r" or "z" : 
            newchar = "b"
                
        if text[i] == "s" or "y" or "v" :        
            newchar = "c"

        if newchar == "" :
            print("Error/ Illegal Character - " + text[i])
            
            if text[i].istitle() == True :
                print("Upper case letters are not supported.")



        printthis = printthis + newchar
        newchar = ""

    print(printthis)
    
        

def main ():
    
    input1 = input()
    
    if input1 == "rnd" :
        print("Encoding mode selected. Please enter a sentence.")
        rndloop()
        
    elif input1 == "unrnd" :
        print("Decoding mode selected. Please enter a code.")
        unrndloop()
        
    else :
        print("Please enter 'rnd' or 'unrnd' to use the program.")
        
    main()    
        
         
         
def rndloop():
    input2 = input()
    
    if input2 != "" :
        print("Working...")
        randomtxt(input2)
    if input2 == "" :
        print("Please Enter a sentence.")    
        
    rndloop()



def unrndloop():
    input2 = input()
    
    if input2 != "" :
        print("Working...")
        unrandomtxt(input2)
    if input2 == "" :
        print("Please Enter a code.")    
        
    unrndloop()
    
print("Randomizer 1.0 - Encodes and Decodes the letters a, b, and our brand new addition, C! Wow!")
print("Please select a mode. (rnd or unrnd)")
    
main()