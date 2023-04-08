package com.cs673.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cs673.backend.entity.Appointment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface AppointmentMapper extends BaseMapper<Appointment> {
    @Select("SELECT * FROM appointments WHERE date BETWEEN #{startDate} AND #{endDate} AND plate = #{plate}")
    List<Appointment> getAppointmentsBetweenDatesAndPlate(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("plate") String plate);
}
