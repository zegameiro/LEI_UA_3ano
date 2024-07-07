import "package:flutter/material.dart";
import "package:weatherapp/cards/settingcard.dart";
import "package:weatherapp/utils/utils.dart";

class SettingPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: Column(
        children: [
          SizedBox(
            height: 400,
            child: ListView.builder(
              itemCount: options.length,
              itemBuilder: (context, index) {
                final settingCard = options[index];
                return buildSettingsCard(
                  label: settingCard.label, 
                  icon: settingCard.icon, 
                  optionalLabel: settingCard.optionalLabel,
                  hasChevron: settingCard.hasChevron,
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

