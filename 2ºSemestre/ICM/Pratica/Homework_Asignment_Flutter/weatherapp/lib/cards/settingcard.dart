import 'package:flutter/material.dart';

class SettingOption {
  final String label;
  final IconData icon;
  final String optionalLabel;
  final bool hasChevron;

  SettingOption({
    required this.label,
    required this.icon,
    required this.optionalLabel,
    required this.hasChevron,
  });
  
  String getLabel() {
    return label;
  }

  IconData getIcon() {
    return icon;
  }

  String getOptionalLabel() {
    return optionalLabel;
  }

  bool getHashChevron() {
    return hasChevron;
  }
}

final List<SettingOption> options = [
  SettingOption(
    label: "Appearance", 
    icon: Icons.brush_outlined, 
    optionalLabel: "",
    hasChevron: true,
  ),
  SettingOption(
    label: "Functions", 
    icon: Icons.terminal_outlined, 
    optionalLabel: "",
    hasChevron: true,
  ),
  SettingOption(
    label: "Data", 
    icon: Icons.brush_outlined, 
    optionalLabel: "",
    hasChevron: true,
  ),
  SettingOption(
    label: "Language", 
    icon: Icons.language_outlined, 
    optionalLabel: "English",
    hasChevron: true,
  ),
  SettingOption(
    label: "Application Version", 
    icon: Icons.app_settings_alt_outlined, 
    optionalLabel: "1.2.4",
    hasChevron: false
  ),
  
];