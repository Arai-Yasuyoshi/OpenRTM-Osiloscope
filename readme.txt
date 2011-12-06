-更新履歴-
2011/12/07 とりあえずソフトウェア公開

■ソフトウェア概要■
産業技術総合研究所が開発を行なっているソフトウェアプラットフォーム"OpenRTM-aist"上で動作する
ソフトウェアです．OpenRTM-aistではソフトウェアの部品であるRTコンポーネント(RTC)を繋ぎあわせる
ことでシステム構築をおこないます．
　このRTCは他のRTCから出力されるデータをグラフ化します．主にOpenRTMを使い始めた初学者を対象と
したRTCで，設定のすべてをGUIでできるようになっています．画面に表示されたグラフは欲しい部分だけ
ボタンひとつでcsv形式で出力することができます．

■実行環境■
Windows7 Professional Editon(64bit)でのみ動作確認済みです．

■動作に必要なソフトウェア■
OpenRTM-aist (Java版) 1.x
最新のjre(Javaのランタイム)
RTSystem EditorなどのRTC接続ツール

■実行方法■
1. ダウンロードした Oosiloscope.zip を解凍
2. Osiloscope/Osiloscope.bat を実行
3. 少し経つとGUI画面が表示されるのでそこでRTCのデータポートの設定を行う
4. "RTCを起動"ボタンを押すと一旦そのGUIは消え，新しいGUIが表示される．
5. ここでRTCとしてネームサーバに登録されるので他のRTCと接続を行う．
5. RTCをActivateする．
以上の流れで異なるRTCのデータをグラフ化することができます．

■ソフトウェア仕様■
・対応しているデータ型
RTC::TimedLong
RTC::TimedShort
RTC::TimedDouble
RTC::TimedFloat
・ファイル出力
csv形式

■今後のアップデート予定■
・Linux, MacOSなどに対応
・サポートするデータ型を追加.(主に配列型)
・グラフの種類を追加．
・出力ファイルの追加(xls,xlsxなど)