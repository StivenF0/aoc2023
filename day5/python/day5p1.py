
lines = []
with open("../input.txt", "r") as file:
    lines = [line.strip("\n") for line in file.readlines()]

seeds = [int(num) for num in lines[0].split(":")[1].strip().split(" ")]

maps = []
for block in "\n".join(lines[2:-1]).split("\n\n"):
    block_list = []
    for _range in block.split("\n")[1::]:
        block_list.append([int(num) for num in _range.split(" ")])
    maps.append(block_list)

for _map in maps:
    for seed_idx in range(len(seeds)):
        for _range in _map:
            if _range[1] <= seeds[seed_idx] <= _range[1] + _range[2]:
                seeds[seed_idx] += _range[0] - _range[1]
                break

print(min(seeds))