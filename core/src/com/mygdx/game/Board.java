package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.*;

import static com.mygdx.game.Main.*;


public class Board {
    int n = 3;
    int m = 3;
    private int[][] board = new int[n][m];

    public Board() {

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                board[x][y] = 3;

            }
        }
    }

    public void display(ShapeRenderer shape) {
        Color color = new Color(1, 1, 1, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                float r = 100;
                // W/n = 200 when * by i sets point in graph
                int x = (WIDTH / n) * i;
                int y = (HIGHT / m) * j;
                if (board[i][j] == 0) {
                    color = new Color(1, 0, 0, 1);
                    shape.set(ShapeType.Line);
                    shape.setColor(color);
                    shape.circle((r + x), (r + y), r);
                }
                if (board[i][j] == 1) {
                    color = new Color(1, 1f, 1, 1);
                    shape.set(ShapeType.Line);
                    shape.setColor(color);
                    shape.line(new Vector2(x, y), new Vector2(WIDTH / n + x, HIGHT / m + y));
                    shape.line(new Vector2(x, 200 + y), new Vector2(200 + x, y));

                }
                if (board[i][j] == 3) {
                    color = new Color(0, 0, 0, 1);
                    shape.setColor(color);
                }

                shape.set(ShapeType.Line);
                shape.setColor(1, 1, 1, 1);
                shape.rect((WIDTH / n) * i, (HIGHT / m) * j, WIDTH / n, HIGHT / m);
            }
        }
    }

    public void place(int player) {

        float x = Gdx.input.getX() / (float) (WIDTH);
        float y = Gdx.input.getY() / (float) HIGHT;
        float indexX = (float) Math.floor(x * 3);
        float indexY = (float) Math.floor(y * 3);
        //cant get ydown to work so 2-;
        board[(int) (indexX % 3)][2 - (int) (indexY % 3)] = player;

    }

    public boolean hasWon() {

        for (int i = 0; i < 3; i++) {
            if (board[i][i] != 3) {
                if (board[i % 3][i % 3] == board[(i+1) % 3][i % 3] && board[i % 3][i % 3] == board[(i+2) % 3][i % 3])
                    return true;
                if (board[i % 3][i % 3] == board[i % 3][(i+ 1) % 3] && board[i % 3][i % 3] == board[i % 3][(i+2) % 3])
                    return true;
            }
        }
        if (board[1][1] != 3) {
            if (board[0][0] == board[1][1] && board[0][0] == board[2][2])
                return true;
            if (board[0][2] == board[1][1] && board[0][2] == board[2][0])
                return true;
        }
            return false;
        }
    }



