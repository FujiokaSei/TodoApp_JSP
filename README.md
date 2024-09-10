# TodoApp_JSP
自分のタスク(やること)を管理するために開発したWEBアプリケーション 

![Todoアプリ](https://user-images.githubusercontent.com/93693358/154421034-97f449ec-76c0-4aed-9200-d0272baca271.png)
## 特徴
・ログイン後は画面遷移が無く一つの画面で操作できることが特徴。  
・タスクの完遂率が表示されるため、達成感が感じられる  
  
## 使い方
### ログイン
1.ログイン画面(http://160.16.139.236:8080/TodoApp_JspServlet/login) に遷移する  
2. ゲストユーザーを利用してログインする  
　 ID:guest  
　 PASS:guest  

### 追加
1.[タスク名][説明][重要度][期限]を入力する  
2.[追加]ボタンを押下する  

### 編集  
1.編集対象のタスクの編集ボタンを押下する  
2.左列のフォームから更新したい項目を編集する  
3.[更新]ボタンを押下する  

### 完了
1.[完了]ボタンを押下する  
　⇒タスクが一覧から削除される  

### 並び替え  
1.右列の[期限順][優先度順]を押下する  
　⇒中央のタスク一覧が並び替えられる


## 使用技術  
HTML, CSS, Bootstrap, JSP, MySQL, Servlet, Java, JavaScript, JDBC, Dao, jQuery

## 文責
* 作成者：FujiokaSei
* E-mail:f.sei3123@gmail.com
