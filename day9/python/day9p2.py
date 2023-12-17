
def checkAllEqualsZero(arr):
    return all(i == 0 for i in arr)

def getDifferenceReport(report):
    new_report = []
    for idx in range(len(report) - 1):
        new_report.append(report[idx + 1] - report[idx])
    return new_report


with open("../input.txt", "r") as file:
    lines = [line.strip("\n") for line in file.readlines()]

reports = [list(map(lambda val: int(val), line.split(" "))) for line in lines]
sum_of_last_reports = 0
for idx, report in enumerate(reports, 0):
    subreports = []
    subreports.append(report)
    while not checkAllEqualsZero(subreports[-1]):
        new_subreport = getDifferenceReport(subreports[-1])
        subreports.append(new_subreport)
    
    
    subreports[-1].append(0)
    rev_subreports = list(reversed(subreports))
    quant = len(rev_subreports)
    for idx in range(quant - 1):
        rev_subreports[idx+1].insert(0, -rev_subreports[idx][0] + rev_subreports[idx+1][0])
    sum_of_last_reports += rev_subreports[-1][0]
print(sum_of_last_reports)