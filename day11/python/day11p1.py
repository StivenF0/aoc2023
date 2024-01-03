
with open("../placeholder.txt", "r") as file:
    lines = [line.rstrip() for line in file.readlines()]

class Universe:
    def __init__(self):
        self.matrix = []
        self.matrix_rows_count = 0
        self.matrix_cols_count = 0

    def recount_matrix(self):
        self.matrix_rows_count = len(self.matrix)
        self.matrix_cols_count = len(self.matrix[0])

    def add_row(self, row, idx = -1):
        self.matrix.insert(self.matrix_rows_count if idx == -1 else idx, list(row))
        self.recount_matrix()

    def add_col(self, idx = -1):
        for row_idx in range(self.matrix_rows_count):
            self.matrix[row_idx].insert(self.matrix_cols_count if idx == -1 else idx, ".")
        self.recount_matrix()
    
    def get_rows(self):
        return self.matrix
    
    def get_cols(self):
        cols = []
        for col_idx in range(self.matrix_cols_count):
            col = []
            for row_idx in range(self.matrix_rows_count):
                col.append(self.matrix[row_idx][col_idx])
            cols.append(col)
        return cols

    def get_symbols(self):
        symbols = []
        for i, row in enumerate(self.matrix):
            for j, col in enumerate(row):
                if col == ".": continue
                symbols.append((col, (i, j)))
        return symbols


    def print_universe(self):
        for row in self.matrix:
            print("".join(row))


universe = Universe()

for line in lines:
    universe.add_row(line)

added = 0
for idx, row in list(enumerate(universe.get_rows())):
    ROWTMP = "." * universe.matrix_cols_count
    if "#" in row: continue
    universe.add_row(ROWTMP, idx + added)
    added += 1

added = 0
for idx, col in list(enumerate(universe.get_cols())):
    if "#" in col: continue
    universe.add_col(idx + added)
    added += 1

symbols = map(
    lambda sym: (sym[0], sym[1][1]), 
    enumerate(universe.get_symbols(), 1)
)
symbols = list(symbols)

acc = 0
combinations = []
for fsym in symbols:
    for lsym in symbols:
        if fsym == lsym: continue
        pair = sorted([fsym[0], lsym[0]])
        if pair in combinations: continue
        combinations.append(pair)
        path = \
            abs(symbols[fsym[0]-1][1][0] - symbols[lsym[0]-1][1][0]) + \
            abs(symbols[fsym[0]-1][1][1] - symbols[lsym[0]-1][1][1])
        acc += path

        print(f"{fsym[0]} {lsym[0]} -> {path}")
print(f"Sum: {acc}")
