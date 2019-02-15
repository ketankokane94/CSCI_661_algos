



def partition(array, left, right):
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


if __name__ == '__main__':
    pass


