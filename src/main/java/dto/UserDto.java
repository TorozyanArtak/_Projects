package dto;

public record UserDto(
        Integer id,
        String name,
        String email,
        String gender,
        String status
) {}

