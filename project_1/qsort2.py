import generate_dataset

def find_min(unsorted_list,from_index=1):
    """
    This function returns a minimum element in a list from given index also
    notes the number of comparisons made during finding the min element
    :param unsorted_list:
    :param from_index:
    :param comparisons:
    :return:
    """
    # assign initial min value to the maximum possible number in this
    # scenario anything more than 100000 is fine
    min = 99999999
    min_element_index = 0
    # iterate from given index upto the length of list
    for index in range(from_index,len(unsorted_list)):
        # every if condition can be considered as an comparison made

        if unsorted_list[index] < min:
            # if a smaller element compared to min is found then update it
            min = unsorted_list[index]
            min_element_index = index
    # return the minimum element, its zero based index from the list and
    # number of comparisons required to find the min element
    return min,min_element_index



def ssort(unsorted_list):
    """
    this function sorts an unsorted list using selection sort
    :param unsorted_list:
    :return:  a tuple of sorted list and number of comparisons, eg ([1,2],3)
    """

    for position_to_sort in range(len(unsorted_list)-1):
        min,index  = find_min(unsorted_list,position_to_sort)
        if unsorted_list[position_to_sort]  > min:
            unsorted_list[position_to_sort], unsorted_list[index] = unsorted_list[index], unsorted_list[position_to_sort]
    return  unsorted_list


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
    if left < right and len(array) > 9:
        split_point = partition(array, left, right)
        array = quick_sort(left,split_point-1,array)
        array = quick_sort(split_point+1,right,array)
    elif left < right:
        array = ssort(array)
    return array


def verify(array):
    for i in range(len(array) -1):
        if array[i] <= array[i+1]:
            i += 1
        else:
            print('Not sorted')
            print(array[i],array[i+1])
            return
    print('success')



if __name__ == '__main__':
    pass
    #, 10000, 50000, 100000, 500000
    for N in [100]:
        array = quick_sort(0, N - 1, generate_dataset.generate_data_set_a(N))
        verify(array)
    print(array)
