# Get lines from the input file
lines = []
with open("../input.txt", "r") as file:
    lines = [line.strip("\n") for line in file.readlines()]

# Defining a list of possible numbers
numbers_list = [str(i) for i in range(1,10)]

# Get the numbers from the lines
filtered_lines = []
for line in lines:
    filtered_line = []
    for char in line:
        if char in numbers_list:
            filtered_line.append(char)
    filtered_lines.append(filtered_line)

# Filtering the first and last number for each line
numbers = []
for line in filtered_lines:
    numbers.append(int(line[0] + line[-1]))

# Get the sum of the numbers
print(sum(numbers))