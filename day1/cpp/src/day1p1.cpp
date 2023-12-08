#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>

bool contains(std::vector<char> numbersList, char character) {
  return (std::find(numbersList.begin(), numbersList.end(), character) != numbersList.end()) ? true : false;
}

int concatConvert(char c1, char c2) {
  std::string s1, s2;
  s1 = std::string(1, c1);
  s2 = std::string(1, c2);
  return stoi(s1 + s2);
}

int main() {
  std::vector<std::string> lines;
  std::fstream input("../../input.txt");
  std::string line;
  while(std::getline(input, line)) {
    lines.push_back(line);
  }
  input.close();

  std::vector<char> numbersList;
  for (int i = 1; i <= 9; i++) {
    numbersList.push_back((char) i + '0');
  }

  std::vector<std::vector<char>> filteredLines;
  std::vector<char> filteredLine;
  for (std::string line : lines) {
    filteredLine.clear();
    for (char character : line) {
      if (contains(numbersList, character)){
        filteredLine.push_back(character);
      }
    }
    filteredLines.push_back(filteredLine);
  }

  std::vector<int> numbers;
  for (std::vector<char> line : filteredLines) {
    numbers.push_back(concatConvert(line.front(), line.back()));
  }

  int sum = 0;
  for (int num : numbers) {
    sum += num;
  }

  std::cout << "Sum: " << sum << '\n';
  
  return 0;
}