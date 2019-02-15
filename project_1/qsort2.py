import generate_dataset, qsort


def insertion_sort(arr):

    for i in range(len(arr)):
        cursor = arr[i]
        pos = i

        while pos > 0 and arr[pos - 1] > cursor:
            # Swap the number down the list
            arr[pos] = arr[pos - 1]
            pos = pos - 1
        # Break and do the final swap
        arr[pos] = cursor

    return arr


def quick_sort(left,right, array):
    if left < right and (right - left + 1) > 10:
        split_point = qsort.partition(array, left, right)
        array = quick_sort(left,split_point-1,array)
        array = quick_sort(split_point+1,right,array)
    elif left < right:
        array[left:right+1] = insertion_sort(array[left:right+1])
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
