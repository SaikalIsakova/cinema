package kg.mega.cinema.models.responses;

import kg.mega.cinema.models.enums.SeatStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatScheduleResponse {


        Long seatScheduleId;
        SeatStatus status;
        int row;
        int seatNum;


        @Override
        public String toString() {
                return "SeatScheduleResponse{" +
                        "seatScheduleId=" + seatScheduleId +
                        ", status=" + status +
                        ", row=" + row +
                        ", seatNum=" + seatNum +
                        '}';
        }
}
