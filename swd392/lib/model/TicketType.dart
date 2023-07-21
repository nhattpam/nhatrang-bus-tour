class TicketType {
  int? ticketTypeId;
  String? ticketTypeName;
  List<PriceFrameTicket>? priceFrameTicket;

  TicketType({this.ticketTypeId, this.ticketTypeName, this.priceFrameTicket});

  TicketType.fromJson(Map<String, dynamic> json) {
    ticketTypeId = json['ticketTypeId'];
    ticketTypeName = json['ticketTypeName'];
    if (json['priceFrameTicket'] != null) {
      priceFrameTicket = <PriceFrameTicket>[];
      json['priceFrameTicket'].forEach((v) {
        priceFrameTicket!.add(new PriceFrameTicket.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['ticketTypeId'] = this.ticketTypeId;
    data['ticketTypeName'] = this.ticketTypeName;
    if (this.priceFrameTicket != null) {
      data['priceFrameTicket'] =
          this.priceFrameTicket!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class PriceFrameTicket {
  int? priceFrameTicketId;
  double? price;

  PriceFrameTicket({this.priceFrameTicketId, this.price});

  PriceFrameTicket.fromJson(Map<String, dynamic> json) {
    priceFrameTicketId = json['priceFrameTicketId'];
    price = json['price'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['priceFrameTicketId'] = this.priceFrameTicketId;
    data['price'] = this.price;
    return data;
  }
}
