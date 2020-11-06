#### sample pubdub cloudEvent awacs cloud generates

```
2020-11-06 18:12:14.555  INFO 16472 --- [bsub-publisher1] c.a.s.p.config.GooglePubSubConfig        : Message was sent successfully.
2020-11-06 18:12:14.564  INFO 16472 --- [sub-subscriber3] c.a.s.p.config.GooglePubSubConfig        : Message arrived! Payload: {
  "eventId": "bdf441c4-049e-4fc8-8bcd-3ef5986dfcdc",
  "eventName": "awacs.cloud.product.order.get",
  "eventSource": "product-service",
  "eventType": "order:3",
  "sessionId": "1D418007417AB43E76F7B6579E61A745",
  "authToken": "[Bearer 0c9880c7-c94a-4e88-8d51-331e6d9995ce]",
  "userId": "a",
  "sessionCreatedAt": "1604665645640",
  "eventCreatedAt": "2020-11-06T18:12:14.2937135"
}

```