class activity:
    __slots__ = 'start', 'finish', 'name'


    def __init__(self,name,start,finish):
        self.name = name
        self.start = start
        self.finish = finish


A = []
scheduled = []


def create_activities():
    A.append(activity(1,1,4))
    A.append(activity(2, 3, 5))
    A.append(activity(3, 0, 6))
    A.append(activity(4, 5, 7))
    A.append(activity(5, 3, 8))
    A.append(activity(6, 5, 9))
    A.append(activity(7, 6, 10))
    A.append(activity(8, 8, 11))
    A.append(activity(9, 8, 12))
    A.append(activity(10, 2, 13))
    A.append(activity(11, 12, 14))




def sort_activities_based_finish_time():
    pass


def print_timeline():

    for act in scheduled:
        print(act.name,act.start,act.finish)



if __name__ == '__main__':
    #create activities
    create_activities()
    #sort_activities_based_finish_time()


    for act in A:
        if len(scheduled) == 0:
            scheduled.append(act)
        else:
            if scheduled[len(scheduled)-1].finish <= act.start:
                scheduled.append(act)
     
    print_timeline()
