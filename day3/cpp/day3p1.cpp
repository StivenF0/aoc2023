#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <array>
#include <algorithm>
#include <map>

// Defining types to simplify comprehension
using coords_t = std::array<int, 2>;
using coordsArray_t = std::vector<coords_t>;
using number_t = std::map<std::string, std::string>;
using numberArray_t = std::vector<std::map<std::string, std::string>>;

// Simplify an string removing a specific character
void stripStr(std::string &str, char character) {
  str.erase(remove(str.begin(), str.end(), character), str.end());
}

// Verifying if the string is numerical
bool isNumber(const std::string &str) {
  if (str.empty())
    return false;

  for (char c : str) {
    if (!isdigit(c))
      return false;
  }

  return true;
}

// Get coordinates for all symbols
coordsArray_t getAllSymbolCoords(std::vector<std::string> &matrix) {
  coordsArray_t allCoords;
  for (int i = 0; i < matrix.size(); i++) {
    for (int j = 0; j < matrix[i].length(); j++) {
      if (matrix[i][j] == '.' || isdigit(matrix[i][j]))
        continue;
      allCoords.push_back({j, i});
    }
  }
  return allCoords;
}

// Get all numbers mapped by his value, x, y, and digits as size.
numberArray_t getAllNumbers(std::vector<std::string> &matrix) {
  numberArray_t allNums;
  number_t num;
  std::string line;
  for (int line_idx = 0; line_idx < matrix.size(); line_idx++) {
    line = matrix[line_idx];
    int char_idx = 0;
    while (char_idx < line.size()) {
      num.clear();
      bool found = false;
      std::string substr;
      for (int leng = line.size() - char_idx; leng > 0; leng--) {
        substr = line.substr(char_idx, leng);
        if (!isNumber(substr))
          continue;
        found = true;
        num.insert(std::make_pair("value", substr));
        num.insert(std::make_pair("x", std::to_string(char_idx)));
        num.insert(std::make_pair("y", std::to_string(line_idx)));
        num.insert(std::make_pair("size", std::to_string(leng)));
        num.insert(std::make_pair("found", ""));
        allNums.push_back(num);
        char_idx += leng;
        break;
      }
      if (!found)
        char_idx++;
    }
  }

  return allNums;
}

int main() {
  // Reading the input file
  std::vector<std::string> lines;
  std::fstream input("../input.txt");
  std::string line;
  while (std::getline(input, line)) {
    lines.push_back(line);
  }
  input.close();

  // Getting information to work with the data
  coordsArray_t coords = getAllSymbolCoords(lines);
  numberArray_t nums = getAllNumbers(lines);

  // Mapping through each coordinate to find his matching numbers
  for (coords_t coord : coords) {
    for (number_t& num : nums) {
      if (
          // Verify up
          (
              std::stoi(num["y"]) == coord[1] - 1 &&
              std::stoi(num["x"]) <= coord[0] + 1 &&
              std::stoi(num["x"]) >= coord[0] - std::stoi(num["size"])) ||
          // Verify down
          (
              std::stoi(num["y"]) == coord[1] + 1 &&
              std::stoi(num["x"]) <= coord[0] + 1 &&
              std::stoi(num["x"]) >= coord[0] - std::stoi(num["size"])) ||
          // Verify left
          (
              std::stoi(num["y"]) == coord[1] &&
              std::stoi(num["x"]) == coord[0] - std::stoi(num["size"])) ||
          // Verify right
          (
              std::stoi(num["y"]) == coord[1] &&
              std::stoi(num["x"]) == coord[0] + 1))
      {
        num["found"] = "1";
      }
    }
  }

  // Summing the values
  int sum = 0;
  for (number_t num : nums) {
    if (num["found"].empty()) continue;
    sum += std::stoi(num["value"]);
  }

  // Printing the output
  std::cout << sum << '\n';

  return 0;
}