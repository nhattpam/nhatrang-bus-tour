class Bus {
  int? busId;
  String? busNumber;
  int? seat;

  Bus({this.busId, this.busNumber, this.seat});

  Bus.fromJson(Map<String, dynamic> json) {
    busId = json['busId'];
    busNumber = json['busNumber'];
    seat = json['seat'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['busId'] = this.busId;
    data['busNumber'] = this.busNumber;
    data['seat'] = this.seat;
    return data;
  }
}