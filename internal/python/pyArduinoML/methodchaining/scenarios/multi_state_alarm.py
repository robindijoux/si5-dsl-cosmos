def scenario4():
    """
    Direct use of the DSL.
    + : auto-completion (limited due to python typing system)
    - : verbose, Python syntax requires '\' to cut lines.

    :return:
    """
    import sys
    sys.path.append('..')
    from AppBuilder import AppBuilder
    from model.SIGNAL import HIGH, LOW

    app = AppBuilder("Scenario4") \
        .sensor("BUTTON").on_pin(9) \
        .actuator("LED").on_pin(12) \
        .actuator("BUZZER").on_pin(11) \
        .state("off") \
            .set("LED").to(LOW) \
            .set("BUZZER").to(LOW) \
            .when("BUTTON").has_value(HIGH).go_to_state("buzz") \
        .state("buzz") \
            .set("LED").to(LOW) \
            .set("BUZZER").to(HIGH) \
            .when("BUTTON").has_value(HIGH).go_to_state("led") \
        .state("led") \
            .set("LED").to(HIGH) \
            .set("BUZZER").to(LOW) \
            .when("BUTTON").has_value(HIGH).go_to_state("off") \
        .get_contents()

    print(app)


if __name__ == '__main__':
    scenario4()
