package com.janken.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Janken {

	public static void main(String[] args) {
		int maxPlayTimes = 10;
		printTitle();
		printSelectPlayLength(maxPlayTimes);
		int playTimes = inputPositiveInt(maxPlayTimes);
		if (playTimes == 0) {
			end();
			return;
		}
		List<String> result = start(playTimes);
		printGameResult(result);
		end();
	}

	public static void printTitle() {
		print(getSeparator());
		print("Janken Game");
		print(getSeparator());
	}

	public static void printSelectPlayLength(int max) {
		print("遊ぶ回数を入力してね");
		print(max + "回まで遊べるよ");
	}

	public static int inputPositiveInt(int max) {
		var scanner = new Scanner(System.in);
		boolean notPositive = true;
		int input = 0;
		while (notPositive) {
			while (!scanner.hasNextInt()) {
				print("数値を入力してね");
				scanner.next();
			}
			input = scanner.nextInt();
			if (input < 0 || input > max) {
				print("0～" + max + "で入力してね");
				continue;
			}
			notPositive = false;
		}
		return input;
	}

	public static List<String> start(int playTimes) {
		List<String> result = new ArrayList<>(playTimes);
		for (int i = 0; i < playTimes; i++) {
			int turn = i + 1;
			int remaining = playTimes - turn;
			String judge = battle(turn, remaining);
			while (judge == "あいこ") {
				print("あいこなのでもう一回");
				judge = battle(turn, remaining);
			}
			result.add(judge);
		}
		return result;
	}

	public static String battle(int turn, int remaining) {
		String[] janken = { "グー", "チョキ", "パー" };
		int handLength = janken.length;
		int handMax = handLength - 1;
		printGameNavigation(turn);
		int player = inputPositiveInt(handMax);
		int computer = getRandom(handLength);
		String judge = judge(player, computer);
		printBattleResult(janken[player], janken[computer], judge, remaining);
		return judge;
	}

	public static void printGameNavigation(int turn) {
		print(getSeparator());
		print(turn + "回目の勝負");
		print("出す手を選んでね");
		print("グー ： 0");
		print("チョキ ： 1");
		print("パー ： 2");
		print(getSeparator());
	}

	public static void printBattleResult(String player, String computer, String judge, int remaining) {
		print(getSeparator());
		print("あなた　：　" + player);
		print("COM　：　" + computer);
		print("結果　：　" + judge);
		print("残りの勝負回数　：　" + remaining);
		print(getSeparator());
	}

	public static int getRandom(int endExclusive) {
		return new Random().nextInt(endExclusive);
	}

	public static String judge(int player, int computer) {
		if (player == computer) {
			return "あいこ";
		}
		int winPattern = getWinPattern(player);
		if (winPattern == computer) {
			return "かち";
		}
		return "まけ";
	}

	public static int getWinPattern(int player) {
		if (player == 2) {
			return 0;
		}
		return player + 1;
	}

	public static void printGameResult(List<String> result) {
		print(getSeparator());
		print("ゲーム結果");
		result.stream().forEach(System.out::println);
		print(getSeparator());
	}

	public static void end() {
		print(getSeparator());
		print("ゲームを終了します");
		print("また遊んでね");
		print(getSeparator());
	}

	public static void print(String message) {
		System.out.println(message);
	}

	public static String getSeparator() {
		return "=".repeat(20);
	}

}
