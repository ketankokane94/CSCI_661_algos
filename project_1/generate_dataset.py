import random
import numpy as np


def generate_data_set_a(n):
    list_of_integers_upto_n = list(range(n))
    random.shuffle(list_of_integers_upto_n)
    return list_of_integers_upto_n


def generate_data_set_b(n):
    result = np.random.poisson(n/2,n)
    return list(result)


if __name__ == "__main__":
    pass
