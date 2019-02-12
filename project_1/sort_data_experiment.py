import generate_dataset, time , numpy
import qsort as sort1


def verify(array):
    for i in range(len(array) -1):
        if array[i] <= array[i+1]:
            i += 1
        else:
            print('Not sorted')
            print(array[i],array[i+1])

            return


def run(N):
    s = time.time()
    array = sort1.quick_sort(0, N - 1, generate_dataset.generate_data_set_b(N))
    e = time.time()
    return array,e - s


if __name__ == "__main__":
    print('Input Length'.ljust(20),'CPU Time'.ljust(20),end='\t')
    print()
    for N in [1000,10000,50000,100000,500000]:
        recorded_time = []
        for executions in range(19):
            sorted_array, time_required = run(N)
            recorded_time.append(time_required)

        mean_ = numpy.mean(numpy.array(recorded_time))
        print(str(N).ljust(20), str(mean_).ljust(20),end='\t')
        print()


        #print("Verifying array of size " ,N )
        '''
        verify function was used to verify if the given sorted array is 
        actually sorted
        '''
        #verify(sorted_array)
