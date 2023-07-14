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