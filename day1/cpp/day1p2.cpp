#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>

bool contains(std::vector<std::string> numbersList, std::string str) {
  return (std::find(numbersList.begin(), numbersList.end(), str) != numbersList.end()) ? true : false;
}

void removeEmptyString(std::vector<std::string> stringVector) {
  stringVector.erase(
    std::remove_if(
      stringVector.begin(), stringVector.end(),
      [](const std::string& inter)
      { return inter == ""; }
    ), 
    stringVector.end()
  );
}

int main() {
  std::vector<std::string> lines;
  std::fstream input("../input.txt");
  std::string line;
  while(std::getline(input, line)) {
    lines.push_back(line);
  }
  input.close();

  std::map<std::string, std::string> numbersDict = {
    {"1", "1"}, {"one", "1"},
    {"2", "2"}, {"two", "2"},
    {"3", "3"}, {"three", "3"},
    {"4", "4"}, {"four", "4"},
    {"5", "5"}, {"five", "5"},
    {"6", "6"}, {"six", "6"},
    {"7", "7"}, {"seven", "7"},
    {"8", "8"}, {"eight", "8"},
    {"9", "9"}, {"nine", "9"}
  };

  // Extract keys into a vector
  std::vector<std::string> numbersDictKeys;
  for (const auto& pair : numbersDict) {
    numbersDictKeys.push_back(pair.first);
  }

  std::vector<std::vector<std::string>> filteredLines;
  std::vector<std::string> filteredLine;
  std::vector<std::string> intervals;
  for (std::string line : lines) {
    intervals.clear();
    for (int start = 0; start < line.size(); start++) {
      for (int end = 0; end < line.size(); end++) {
        intervals.push_back(line.substr(start, end - start + 1));
      }
    }
    removeEmptyString(intervals);

    filteredLine.clear();
    for (std::string interval : intervals) {
      if (contains(numbersDictKeys, interval)){
        filteredLine.push_back(interval);
      }
    }
    filteredLines.push_back(filteredLine);
  }

  std::vector<int> numbers;
  for (std::vector<std::string> line : filteredLines) {
    numbers.push_back(stoi(numbersDict[line.front()] + numbersDict[line.back()]));
  }

  int sum = 0;
  for (int num : numbers) {
    sum += num;
  }

  std::cout << "Sum: " << sum << '\n';
  
  return 0;
}