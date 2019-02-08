import generate_dataset


def get_pivot_value(array,l,r):
    return l


def partition(array, left, right):
    #pivot = get_pivot_value(array,left,right)
    pivot_value = array[left]
    left_marker = left
    right_marker = right
    while left_marker < right_marker:
        while left_marker <= right and array[left_marker] <= pivot_value:
            left_marker += 1
        while left <= right_marker and array[right_marker] > pivot_value:
            right_marker -= 1

        if left_marker < right_marker:
            array[left_marker], array[right_marker] = array[right_marker],array[left_marker]

    array[left],array[right_marker] = array[right_marker],array[left]
    return right_marker


def quick_sort(left,right, array):
    if left < right:
        split_point = partition(array, left, right)
        array = quick_sort(left,split_point-1,array)
        array = quick_sort(split_point+1,right,array)
    return array


def verify(array):

    for i in range(len(array) -1):
        if array[i] <= array[i+1]:
            i+=1
        else:
            print('Not sorted')
            print(array[i],array[i+1])

            return

if __name__ == "__main__":
    for N in [1000,10000,50000,100000,500000]:
    #for N in [21]:
        array = quick_sort(0, N-1,generate_dataset.generate_data_set_b(N))
        print("Verifying array of size " ,N )
        verify(array)




