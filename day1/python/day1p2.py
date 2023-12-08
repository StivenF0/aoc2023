# Get lines from the input file
lines = []
with open("../input.txt", "r") as file:
    lines = [line.strip("\n") for line in file.readlines()]

# Defining a dictionary of numbers
numbers_dict = {
    "1": "1", "one": "1",
    "2": "2", "two": "2",
    "3": "3", "three": "3",
    "4": "4", "four": "4",
    "5": "5", "five": "5",
    "6": "6", "six": "6",
    "7": "7", "seven": "7",
    "8": "8", "eight": "8",
    "9": "9", "nine": "9",
}

filtered_lines = []
for line in lines:
    # Get all intervals possible in the lines
    intervals = []
    for start in range(len(line)):
        for end in range(len(line)):
            intervals.append(line[start:end+1])
    intervals = list(filter(lambda inter: inter != "", intervals))

    # Get the "numbers" from the lines
    filtered_line = []
    for interval in intervals:
        if interval in numbers_dict.keys():
            filtered_line.append(interval)
    filtered_lines.append(filtered_line)

# Filtering to get just the first and last number corresponding to the dictionary
numbers = []
for line in filtered_lines:
    numbers.append(int(numbers_dict[line[0]] + numbers_dict[line[-1]]))

# Print the sum of the numbers
print(sum(numbers))