import generate_dataset

def get_pivot_value(array,l,r):
    return array[l]

def partition(array, left, right):
    pivot_value = get_pivot_value(array,left,right)
    left_marker = left
    right_marker = right
    while left_marker < right_marker:
        swap = False
        if array[left_marker] <= pivot_value:
            left_marker += 1
            swap = True
            
    
        if  array[right_marker] > pivot_value:
            right_marker -= 1
            swap = 
            

            # we need to swap the element at the left and the right marker
        array[left_marker], array[right_marker] = array[right_marker],array[left_marker]
        left_marker += 1
        right_marker -= 1
        
    return left_marker



def quick_sort(left,right, array):
    if left < right:
        split_point = partition(array, left, right)
        quick_sort(left,split_point-1,array)
        quick_sort(split_point+1,right,array)
    print(array)




if __name__ == "__main__":
    for N in [3]:
        quick_sort(0, N-1,generate_dataset.generate_data_set_a(N))


