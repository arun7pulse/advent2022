print("Advent of Code 2022")
def print_decrease(n):
    print(n)
    if(n==1): 
        print(1)
        return
    return print_decrease(n-1)
if __name__  == '__main__':
    print_decrease(10)