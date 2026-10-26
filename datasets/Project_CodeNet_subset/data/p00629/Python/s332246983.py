# -*- coding: utf-8 -*-
from sys import stdin
from operator import itemgetter

while 1:
  n = int(stdin.readline().rstrip())
  if n == 0:
    break
  teams = []
  #選抜されたチーム
  Selected_team_id = []
  cnt_selected_teams = 0
  #所属ごとのランキング（所属ごとの選抜チーム数）
  dic_shozoku_and_cntSelected = {}

  for i in range(n):
    * t, = map(int, stdin.readline().split())
    teams.append(t)
    if not dic_shozoku_and_cntSelected.__contains__(t[1]):
      dic_shozoku_and_cntSelected[t[1]] = 0

  sorted_teams1 = sorted(teams, key=itemgetter(3, 0))  # ペナルティ、ID
  sorted_teams = sorted(sorted_teams1, key=itemgetter(2), reverse=True)  # 正解数
  for t in sorted_teams:
    cnt_same_shozoku = dic_shozoku_and_cntSelected[t[1]]
    if cnt_selected_teams < 10:
      if cnt_same_shozoku < 3:
        Selected_team_id.append(t[0])
        dic_shozoku_and_cntSelected[t[1]] += 1
        cnt_selected_teams += 1
    elif cnt_selected_teams < 20:
      if cnt_same_shozoku < 2:
        Selected_team_id.append(t[0])
        dic_shozoku_and_cntSelected[t[1]] += 1
        cnt_selected_teams += 1
    elif cnt_selected_teams < 26:
      if cnt_same_shozoku < 1:
        Selected_team_id.append(t[0])
        dic_shozoku_and_cntSelected[t[1]] += 1
        cnt_selected_teams += 1

  print("\n".join(map(str,Selected_team_id)))

