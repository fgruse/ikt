from random import randint

for x in range(10000):
    node = "O," + str(x) + "," + str(randint(0,100000)) + "," + str(randint(0,100000))
    print(node)

for x in range(50000):
    edge = "S," + str(randint(0,9999)) + "," + str(randint(0,9999))
    print(edge)
