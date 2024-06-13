#include <bits/stdc++.h>
using namespace std;

#define N 4

int leftDiagonal[30] = { 0 };
int rightDiagonal[30] = { 0 };
int column[30] = { 0 };

void printSolution(int board[N][N]) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            cout << " " << (board[i][j] == 1 ? "Q" : ".") << " ";
        cout << endl;
    }
}

bool solveNQueenUtil(int board[N][N], int col) {
    if (col >= N)
        return true;

    for (int i = 0; i < N; i++) {
        if ((leftDiagonal[i - col + N - 1] != 1 && rightDiagonal[i + col] != 1) && column[i] != 1) {
            board[i][col] = 1;
            leftDiagonal[i - col + N - 1] = rightDiagonal[i + col] = column[i] = 1;

            if (solveNQueenUtil(board, col + 1))
                return true;

            board[i][col] = 0;
            leftDiagonal[i - col + N - 1] = rightDiagonal[i + col] = column[i] = 0;
        }
    }
    return false;
}

bool solveNQueen() {
    int board[N][N] = { { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 },
                        { 0, 0, 0, 0 } };

    if (!solveNQueenUtil(board, 0)) {
        cout << "Solution does not exist";
        return false;
    }

    printSolution(board);
    return true;
}

int main() {
    solveNQueen();
    return 0;
}
