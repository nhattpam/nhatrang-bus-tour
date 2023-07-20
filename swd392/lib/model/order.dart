class Order {
  int? orderId;
  String? orderDate;
  double? totalPrice;
  int? userId;
  int? paymentId;
  List<Ticket>? ticket;

  Order(
      {this.orderId,
        this.orderDate,
        this.totalPrice,
        this.userId,
        this.paymentId,
        this.ticket});

  Order.fromJson(Map<String, dynamic> json) {
    orderId = json['orderId'];
    orderDate = json['orderDate'];
    totalPrice = json['totalPrice'];
    userId = json['userId'];
    paymentId = json['paymentId'];
    if (json['ticket'] != null) {
      ticket = <Ticket>[];
      json['ticket'].forEach((v) {
        ticket!.add(new Ticket.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['orderId'] = this.orderId;
    data['orderDate'] = this.orderDate;
    data['totalPrice'] = this.totalPrice;
    data['userId'] = this.userId;
    data['paymentId'] = this.paymentId;
    if (this.ticket != null) {
      data['ticket'] = this.ticket!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Ticket {
  int? ticketId;
  String? passengerName;
  String? passengerPhone;
  String? passengerEmail;
  String? feedback;

  Ticket(
      {this.ticketId,
        this.passengerName,
        this.passengerPhone,
        this.passengerEmail,
        this.feedback});

  Ticket.fromJson(Map<String, dynamic> json) {
    ticketId = json['ticketId'];
    passengerName = json['passengerName'];
    passengerPhone = json['passengerPhone'];
    passengerEmail = json['passengerEmail'];
    feedback = json['feedback'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['ticketId'] = this.ticketId;
    data['passengerName'] = this.passengerName;
    data['passengerPhone'] = this.passengerPhone;
    data['passengerEmail'] = this.passengerEmail;
    data['feedback'] = this.feedback;
    return data;
  }
}
