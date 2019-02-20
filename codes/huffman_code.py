class word_frequecy:
    __slots__ = 'word','frequency'

    def __init__(self,word,frequency):
        self.word = word
        self.frequency = frequency


'''

a,45
b,13
c,12
f,12
d,16
e,9
'''
#frequencies
frequency = []


def create_frequencies():

    frequency.append('a', 45)
    frequency.append('d', 16)
    frequency.append('b', 13)
    frequency.append('c', 12)
    frequency.append('f', 12)
    frequency.append('e', 9)


def add_to_heap():
    pass


if __name__ == '__main__':
    create_frequencies()
    add_to_heap()

