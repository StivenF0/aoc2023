#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <array>
#include <algorithm>
#include <map>

using run_t = std::pair<int,int>;
using runArray_t = std::vector<run_t>;

std::vector<std::string> split(std::string str, char separator) {
  std::vector<std::string> newVector;
  std::string word = "";
  for (char character : str) {
    if (character == separator) {
      newVector.push_back(word);
      word = "";
      continue;
    }
    word += std::string(1, character);
  }
  if (!word.empty()) {
    newVector.push_back(word);
  }
  return newVector;
}

std::vector<std::string> filterRemoveStr(std::vector<std::string> vector, std::string elem) {
  std::vector<std::string> newVector;
  for (const std::string& item : vector) {
    if (item == elem) continue;
    newVector.push_back(item);
  }
  return newVector;
}

std::vector<int> mapConvertInt(std::vector<std::string> vector) {
  std::vector<int> newVector;
  for (const std::string& item : vector) {
    newVector.push_back(std::stoi(item));
  }
  return newVector;
}

runArray_t getRuns(std::vector<std::string> lines) {
  runArray_t result;
  
  std::vector<std::string> timestr = split(split(lines[0], ':')[1], ' ');
  timestr = filterRemoveStr(timestr, "");
  std::vector<int> time = mapConvertInt(timestr);

  std::vector<std::string> distancestr = split(split(lines[1], ':')[1], ' ');
  distancestr = filterRemoveStr(distancestr, "");
  std::vector<int> distance = mapConvertInt(distancestr);

  for (int idx = 0; idx < time.size(); idx++) {
    int currentTime = time[idx];
    int currentDistance = distance[idx];
    result.push_back(std::make_pair(currentTime, currentDistance));
  }

  return result;
}

int main() {
  std::vector<std::string> lines;
  std::fstream input("../placeholder.txt");
  std::string line;
  while (std::getline(input, line)) {
    lines.push_back(line);
  }
  input.close();

  runArray_t runs;
  runs = getRuns(lines);
  int totalMargin = 1;
  for (run_t run : runs) {
    int margin = 0;
    // run.first: time
    // run.second: distance
    for (int i = 0; i < run.first + 1; i++) {
      int speed = i;
      int time = run.first - i;
      int traveled = speed * time;
      if (traveled > run.second) margin += 1;
    }
    totalMargin *= margin;
  }

  std::cout << "Margin: " << totalMargin << '\n';

  return 0;
}