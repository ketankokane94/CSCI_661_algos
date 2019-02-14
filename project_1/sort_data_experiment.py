import generate_dataset, time , numpy
import qsort as sort1, qsort2 as sort2


def verify(array):
    for i in range(len(array) -1):
        if array[i] <= array[i+1]:
            i += 1
        else:
            print('Not sorted')
            print(array[i],array[i+1])

            return


def run(sort, dataset_to_use,N):
    array = dataset_to_use(N)
    s = time.time()
    array = sort.quick_sort(0, N - 1, array)
    e = time.time()
    return array,e - s


def execute_run(name, dataset, data, algo):

    print(name + ' algorithm for dataset '+dataset)
    print('Input Length'.ljust(20), 'CPU Time'.ljust(20), end='\t')
    print()

    for N in [1000, 10000, 50000, 100000, 500000]:
        recorded_time = []
        for executions in range(19):
            sorted_array, time_required = run(algo, \
                                              data,
                                              N)
            recorded_time.append(time_required)

        mean_ = numpy.mean(numpy.array(recorded_time))
        print(str(N).ljust(20), str(mean_).ljust(20), end='\t')
        print()



if __name__ == "__main__":

    execute_run('quick sort', 'A', generate_dataset.generate_data_set_a, sort1)
    execute_run('quick sort', 'B', generate_dataset.generate_data_set_b, sort1)
    execute_run('quick sort', 'A', generate_dataset.generate_data_set_a, sort2)
    execute_run('quick sort', 'B', generate_dataset.generate_data_set_b, sort2)