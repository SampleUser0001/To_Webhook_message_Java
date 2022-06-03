# To_Webhook_message_Java
Webhook宛にメッセージ送信する。

## 実行

``` bash
export WEBHOOK_MESSAGE="message"
mvn clean compile exec:java -Dexec.mainClass="sample.webhook.App" -Dexec.args="'${WEBHOOK_MESSAGE}'"
```

### 備考

- 本当は1行で実行できる。
    ``` bash
    export WEBHOOK_URL=
    curl -X POST -H 'Content-type: application/json' --data '{"text":"Hello, World!"}' ${WEBHOOK_URL}
    ```
- ファイルを転送するには別の仕掛けが必要。

## 参考

- [JavaでWebhookを用いてTeamsにメッセージを通知する手順 : 駆け出し物語](http://kakedashi-xx.com:25214/index.php/2021/08/08/post-2954/#toc3)
